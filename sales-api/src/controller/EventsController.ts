import BuyTicket from '../application/usecases/BuyTicket';
import CreateEvent from '../application/usecases/CreateEvent';
import CreateSpots from '../application/usecases/CreateSpots';
import GetEvent from '../application/usecases/GetEvent';
import ListEvents from '../application/usecases/ListEvents';
import ListSpots from '../application/usecases/ListSpots';
import HttpServer from '../infra/http/HttpServer';

export default class EventsController {
  constructor(readonly httpServer: HttpServer, readonly createEvent: CreateEvent, readonly createSpots: CreateSpots, readonly buyTicket: BuyTicket, readonly getEvent: GetEvent, readonly listEvents: ListEvents, readonly listSpots: ListSpots) {
    this.httpServer.on('post', '/events', async (req: any) => {
      const output = await this.createEvent.execute(req.body);
      return output;
    });
    this.httpServer.on('get', '/events', async (req: any) => {
      const output = await this.listEvents.execute();
      return output;
    });
    this.httpServer.on('get', '/events/:eventId', async (req: any) => {
      const output = await this.getEvent.execute(req.params.eventId);
      return output;
    });
    this.httpServer.on('post', '/events/:eventId/create_spots', async (req: any) => {
      const { eventId } = req.params;
      const { numberOfSpots } = req.body;
      const output = await this.createSpots.execute({ eventId, numberOfSpots });
      return output;
    });
    this.httpServer.on('post', '/checkout', async (req: any) => {
      const input = {
        ...req.body,
      };
      const output = await this.buyTicket.execute(input);
      return output;
    });
    this.httpServer.on('get', '/events/:eventId/spots', async (req: any) => {
      const output = await this.listSpots.execute(req.params.eventId);
      return output;
    });
  }
}
