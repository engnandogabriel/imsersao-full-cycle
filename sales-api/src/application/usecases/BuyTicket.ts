import { badRequest, notFound, serverError, success, unauthorized, unprocessableEntity } from '../../domain/Response/Helpers';
import Spot from '../../domain/entity/Spots';
import CreatePartner from '../../infra/gateway/FactoryPartner';
import EventsRepository from '../repository/EventsRepository';
import SpotsRepository from '../repository/SpotsRepository';
import BadRequestError from '../../domain/Exceptions/BadRequestError';
import UnprocessableError from '../../domain/Exceptions/UnprocessableErro';
import BuyTicketDTO from '../../domain/dto/BuyTicketInputDTO';
import Ticket from '../../domain/entity/Tickets';
import ReserveSpotPartnerDTO from '../../domain/dto/ReserveSportPartnerDTO';
import UnauthorizedError from '../../domain/Exceptions/UnauthorizedErrot';
import TicketsRepository from '../repository/TicketsRespotiory';
import HttpResponse from '../../domain/Response/ResponseHttp';

export default class BuyTicket {
  private eventsRepository: EventsRepository;
  private spotsRepository: SpotsRepository;
  private createPartner: CreatePartner;
  private ticketRepositoy: TicketsRepository;
  constructor(eventsRepository: EventsRepository, spotsRepository: SpotsRepository, ticketRepositoy: TicketsRepository, createPartner: CreatePartner) {
    this.eventsRepository = eventsRepository;
    this.spotsRepository = spotsRepository;
    this.ticketRepositoy = ticketRepositoy;
    this.createPartner = createPartner;
  }
  async execute(buyTicketDTO: BuyTicketDTO): Promise<HttpResponse> {
    try {
      const event = await this.eventsRepository.findEventById(buyTicketDTO.eventId);
      if (!event) return notFound(new Error('Event not found'));

      const createPartnerEvent = this.createPartner.create(event.getPartnerId());
      let spot: Spot | void;
      let ticket: Ticket;
      const tickets: Ticket[] = [];
      const spots: Spot[] = [];

      for (let i = 0; i < buyTicketDTO.names.length; i++) {
        spot = await this.spotsRepository.findSpotsByNameAndEventID(event.getId(), buyTicketDTO.names[i]);
        if (!spot) return notFound(new Error(`Spot ${buyTicketDTO.names[i]} not found`));
        if (spot.getStatus() == 'sold') return unauthorized(new UnauthorizedError(`Ticket ${buyTicketDTO.names[i]} already reserved`));
        ticket = Ticket.create(spot.getEventId(), spot.getId(), buyTicketDTO.ticketKind, event.getPrice());
        spot.setTicketId(ticket.getId());
        spot.setSold();
        tickets.push(ticket);
        spots.push(spot);
      }
      const reserveSpotPartnerDTO: ReserveSpotPartnerDTO = {
        email: buyTicketDTO.email,
        ticketKind: buyTicketDTO.ticketKind,
        names: buyTicketDTO.names,
      };
      await createPartnerEvent.reserveSpot(event.getEventPartner(), reserveSpotPartnerDTO);
      for (let i = 0; i < spots.length; i++) {
        await this.spotsRepository.reserveSport(spots[i].getId());
        await this.ticketRepositoy.createTicket(tickets[i]);
      }

      return success({ message: 'Tickets reserveds', data: tickets });
    } catch (error: any) {
      if (error instanceof UnprocessableError) return unprocessableEntity(error);
      if (error instanceof BadRequestError) return badRequest(error);
      return serverError(new Error('Internal Sever Error'));
    }
  }
}
