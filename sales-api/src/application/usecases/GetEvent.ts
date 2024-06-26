import BadRequestError from '../../domain/Exceptions/BadRequestError';
import UnprocessableError from '../../domain/Exceptions/UnprocessableErro';
import { badRequest, notFound, serverError, success, unprocessableEntity } from '../../domain/Response/Helpers';
import HttpResponse from '../../domain/Response/ResponseHttp';
import EventsRepository from '../repository/EventsRepository';

export default class GetEvent {
  private eventRepository: EventsRepository;
  constructor(eventRepository: EventsRepository) {
    this.eventRepository = eventRepository;
  }
  async execute(eventId: string): Promise<HttpResponse> {
    try {
      const event = await this.eventRepository.findEventById(eventId);
      if (!event) return notFound(new Error('Event not found'));

      return success({
        message: 'Event',
        data: {
          id: event.getId(),
          name: event.getName(),
          organization: event.getOrganization(),
          image_url: event.getImageUrl(),
          rating: event.getRatting(),
          date: event.getDate(),
          capacity: event.getCapacity(),
          price: event.getPrice(),
          partnerId: event.getPartnerId(),
        },
      });
    } catch (error: any) {
      if (error instanceof UnprocessableError) return unprocessableEntity(error);
      if (error instanceof BadRequestError) return badRequest(error);
      return serverError(new Error('Internal Sever Error'));
    }
  }
}
