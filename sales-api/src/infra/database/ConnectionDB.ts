export default interface ConnectionDB {
  connect(): Promise<void>;
  query(query: string, statment: any): Promise<any>;
  close(): Promise<void>;
}
