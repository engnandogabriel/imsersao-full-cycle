import { badRequest, notFound, serverError, success, unprocessableEntity } from '../../domain/Response/Helpers';
import CreateSpotsOutputDTO from '../../domain/dto/CreateSpotsInputDTO';
import Spot from '../../domain/entity/Spots';
import CreatePartner from '../../infra/gateway/FactoryPartner';
import EventsRepository from '../repository/EventsRepository';
import SpotsRepository from '../repository/SpotsRepository';
import BadRequestError from '../../domain/Exceptions/BadRequestError';
import UnprocessableError from '../../domain/Exceptions/UnprocessableErro';
import InvalidParamError from '../../domain/Exceptions/InvalidParamError';
import HttpResponse from '../../domain/Response/ResponseHttp';

export default class CreateSpots {
  private eventsRepository: EventsRepository;
  private spotsRepository: SpotsRepository;
  private createPartner: CreatePartner;
  constructor(eventsRepository: EventsRepository, spotsRepository: SpotsRepository, createPartner: CreatePartner) {
    this.eventsRepository = eventsRepository;
    this.spotsRepository = spotsRepository;
    this.createPartner = createPartner;
  }
  async execute(createSpotsOutputDTO: CreateSpotsOutputDTO): Promise<HttpResponse> {
    try {
      const event = await this.eventsRepository.findEventById(createSpotsOutputDTO.eventId);
      if (!event) return notFound(new Error('Event not found'));
      if (createSpotsOutputDTO.numberOfSpots > event.getCapacity()) throw new InvalidParamError('Number of sopts cannot be granter than capaciy');
      const createPartnerEvent = this.createPartner.create(event.getPartnerId());
      const spots: Spot[] = Spot.generateSpots(createSpotsOutputDTO.eventId, createSpotsOutputDTO.numberOfSpots);
      for (const spot of spots) {
        const response = await createPartnerEvent.createSpots(event.getEventPartner(), spot.getName());
        if (response.status == 'BAD_REQUEST') throw new BadRequestError(response.message);
        await this.spotsRepository.createSport(spot);
      }
      return success({ message: 'Spots created', data: spots });
    } catch (error: any) {
      if (error instanceof UnprocessableError) return unprocessableEntity(error);
      if (error instanceof BadRequestError) return badRequest(error);
      return serverError(new Error('Internal Sever Error'));
    }
  }
}
