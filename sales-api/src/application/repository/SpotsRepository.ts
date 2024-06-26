import Spot from '../../domain/entity/Spots';

export default interface SpotsRepository {
  findSpotByEventID(spotId: string): Promise<Spot | void>;
  findSpotsByNameAndEventID(spotId: string, eventId: String): Promise<Spot | void>;
  createSport(spot: Spot): Promise<void>;
  reserveSport(spotId: String): Promise<void>;
}
