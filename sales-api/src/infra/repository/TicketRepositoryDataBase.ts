import TicketsRepository from '../../application/repository/TicketsRespotiory';
import ConnectionDB from '../database/ConnectionDB';
import Ticket from '../../domain/entity/Tickets';

export default class TicketRepositoryDataBase implements TicketsRepository {
  constructor(readonly connection: ConnectionDB) {}
  async createTicket(ticket: Ticket): Promise<void> {
    await this.connection.connect();
    await this.connection.query('INSERT INTO tickets (id, event_id, spot_id, ticket_kind, price ) VALUES (?, ?, ?, ?, ?) ', [ticket.getId(), ticket.getEventId(), ticket.getSpotId(), ticket.getTicketKind(), ticket.getPrice()]);
    await this.connection.close();
  }
}
