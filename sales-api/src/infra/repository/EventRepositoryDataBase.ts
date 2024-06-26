import EventsRepository from '../../application/repository/EventsRepository';
import ConnectionDB from '../database/ConnectionDB';
import Events from '../../domain/entity/Events';

export default class EventRepositoryDataBase implements EventsRepository {
  constructor(readonly connection: ConnectionDB) {}
  async listEvents(): Promise<void | Events[]> {
    await this.connection.connect();
    const [result] = await this.connection.query('SELECT * FROM events', []);
    await this.connection.close();
    const events: Events[] = [];
    if (result) {
      for (const event of result) {
        events.push(Events.restore(event.id, event.name, event.location, event.organization, event.ratting, event.date, event.image_url, event.capacity, event.price, event.partner_id, event.event_partner));
      }
    }
    return events;
  }

  async findEventById(id: string): Promise<void | Events> {
    await this.connection.connect();
    const [[result]] = await this.connection.query('SELECT * FROM events WHERE id = ?', [id]);
    await this.connection.close();
    if (result) {
      return Events.restore(result.id, result.name, result.location, result.organization, result.ratting, result.date, result.image_url, result.capacity, result.price, result.partner_id, result.event_partner);
    }
  }
  async createEvent(event: Events): Promise<void> {
    await this.connection.connect();
    await this.connection.query('INSERT INTO events (id, name, location, organization, rating, date, image_url, capacity, price, partner_id, event_partner) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', [
      event.getId(),
      event.getName(),
      event.getLocation(),
      event.getOrganization(),
      event.getRatting(),
      event.getDate(),
      event.getImageUrl(),
      event.getCapacity(),
      event.getPrice(),
      event.getPartnerId(),
      event.getEventPartner(),
    ]);
    await this.connection.close();
  }
}
