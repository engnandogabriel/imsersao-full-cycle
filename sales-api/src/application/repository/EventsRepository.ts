import Events from '../../domain/entity/Events';

export default interface EventsRepository {
  findEventById(id: string): Promise<Events | void>;
  createEvent(event: Events): Promise<void>;
}
