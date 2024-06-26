import BadRequestError from '../../domain/Exceptions/BadRequestError';
import UnprocessableError from '../../domain/Exceptions/UnprocessableErro';
import { badRequest, serverError, success, unprocessableEntity } from '../../domain/Response/Helpers';
import EventsDAO from '../dao/EventsDAO';

export default class ListEvents {
  private eventsDAO: EventsDAO;
  constructor(eventsDAO: EventsDAO) {
    this.eventsDAO = eventsDAO;
  }
  async execute() {
    try {
      const events = await this.eventsDAO.listAll();
      return success({
        message: 'Events',
        data: {
          events,
        },
      });
    } catch (error) {
      if (error instanceof UnprocessableError) return unprocessableEntity(error);
      if (error instanceof BadRequestError) return badRequest(error);
      return serverError(new Error('Internal Sever Error'));
    }
  }
}
