import SpotsDAO from '../../application/dao/SpotsDAO';
import ConnectionDB from '../database/ConnectionDB';

export default class SpotsDataBaseDAO implements SpotsDAO {
  constructor(readonly connectionDb: ConnectionDB) {}
  async listAll(eventId: string): Promise<any> {
    await this.connectionDb.connect();
    const [spots] = await this.connectionDb.query('SELECT * FROM spots WHERE event_id = ?', [eventId]);
    await this.connectionDb.close();
    return spots;
  }
}
