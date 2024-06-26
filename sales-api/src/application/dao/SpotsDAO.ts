export default interface SpotsDAO {
  listAll(eventId: string): Promise<any>;
}
