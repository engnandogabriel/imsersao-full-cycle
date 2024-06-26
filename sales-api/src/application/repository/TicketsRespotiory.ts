import Ticket from '../../domain/entity/Tickets';

export default interface TicketsRepository {
  createTicket(ticket: Ticket): Promise<void>;
}
