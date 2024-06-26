import { createPool } from 'mysql2/promise';

import IConnection from './ConnectionDB';
export default class MySqlAdapter implements IConnection {
  connection: any;
  constructor() {}
  async connect() {
    this.connection = createPool({
      database: 'sales',
      host: 'db-node',
      user: 'root',
      password: 'root',
      port: 3306,
    });
  }
  async query(statement: string, data: any): Promise<any> {
    return await this.connection.query(statement, data, true);
  }
  async close(): Promise<void> {
    await this.connection.end();
  }
}
