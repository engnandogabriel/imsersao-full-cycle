import { badRequest, serverError, success, unprocessableEntity } from '../../domain/Response/Helpers';
import HttpResponse from '../../domain/Response/ResponseHttp';
import CreateEvenetInputDTO from '../../domain/dto/CreateEventInputDTO';
import CreateEventPartnerDTO from '../../domain/dto/CreateEventPartnerDTO';
import Events from '../../domain/entity/Events';
import CreatePartner from '../../infra/gateway/FactoryPartner';
import EventsRepository from '../repository/EventsRepository';
import BadRequestError from '../../domain/Exceptions/BadRequestError';
import UnprocessableErro from '../../domain/Exceptions/UnprocessableErro';
import CreateSpots from './CreateSpots';

export default class CreateEvent {
  private eventsRepository: EventsRepository;
  private createPartner: CreatePartner;
  private createSpot: CreateSpots;
  constructor(eventsRepository: EventsRepository, createPartner: CreatePartner, createSpot: CreateSpots) {
    this.eventsRepository = eventsRepository;
    this.createPartner = createPartner;
    this.createSpot = createSpot;
  }
  async execute(input: CreateEvenetInputDTO): Promise<HttpResponse> {
    try {
      const event = Events.create(input.name, input.location, input.organization, input.rating, input.date, input.imageURL, input.capacity, input.price, input.partnerID);
      const createEventPartner = this.createPartner.create(input.partnerID);
      const createPartnerEvent: CreateEventPartnerDTO = {
        name: event.getName(),
        description: '',
        date: event.getDate(),
        price: event.getPrice(),
      };
      const response = await createEventPartner.createEvent(createPartnerEvent);
      if (response.status == 400) throw new BadRequestError(response.data.message);
      if (response.status == 422) throw new UnprocessableErro(response.data.message);
      if (response.status == 500) throw new Error('');
      event.setEventPartner(response.body.id);
      await this.eventsRepository.createEvent(event);
      await this.createSpot.execute({ eventId: event.getId(), numberOfSpots: event.getCapacity() });
      return success({
        message: 'Event create successfully',
        data: { id: event.getId(), name: event.getName(), location: event.getLocation(), organization: input.organization, rating: event.getRatting(), date: event.getRatting(), capacity: event.getCapacity(), imageURL: event.getImageUrl(), price: event.getPrice(), partnerID: event.getPartnerId() },
      });
    } catch (error: any) {
      if (error instanceof UnprocessableErro) return unprocessableEntity(error);
      if (error instanceof BadRequestError) return badRequest(error);
      return serverError(new Error('Internal Sever Error'));
    }
  }
}
