import EventsDAO from '../../application/dao/EventsDAO';
import ConnectionDB from '../database/ConnectionDB';

export default class EventsDataBasaDAO implements EventsDAO {
  constructor(readonly connection: ConnectionDB) {}
  async listAll(): Promise<any> {
    await this.connection.connect();
    const [events] = await this.connection.query('SELECT *FROM events', []);
    await this.connection.close();
    return events;
  }
}
