import SpotsRepository from '../../application/repository/SpotsRepository';
import ConnectionDB from '../database/ConnectionDB';
import Spot from '../../domain/entity/Spots';

export default class SpotRepositoryDataBase implements SpotsRepository {
  constructor(readonly connection: ConnectionDB) {}
  async findSpotByEventID(spotId: string): Promise<Spot | void> {
    await this.connection.connect();
    const [[result]] = await this.connection.query('SELECT * FROM spots WHERE id = ?', spotId);
    await this.connection.close();
    if (result) {
      return Spot.restore(result.id, result.event_id, result.name, result.name, result.ticket_id);
    }
  }
  async findSpotsByNameAndEventID(eventId: string, name: String): Promise<void | Spot> {
    await this.connection.connect();
    const [[result]] = await this.connection.query('SELECT * FROM spots WHERE event_id = ? AND name = ?', [eventId, name]);
    await this.connection.close();
    if (result) {
      return Spot.restore(result.id, result.event_id, result.name, result.status, result.ticket_id);
    }
    return;
  }
  async createSport(spot: Spot): Promise<void> {
    await this.connection.connect();
    await this.connection.query('INSERT INTO spots (id, event_id, name, status, ticket_id) VALUES (?, ?, ?, ?, ?)', [spot.getId(), spot.getEventId(), spot.getName(), spot.getStatus(), spot.getTicketId()]);
    await this.connection.close();
  }
  async reserveSport(spotId: String): Promise<void> {
    await this.connection.connect();
    await this.connection.query('UPDATE spots SET status = "sold" WHERE id = ?', [spotId]);
    await this.connection.close();
  }
}
