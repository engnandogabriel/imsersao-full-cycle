import CreateEventPartner from '../../domain/dto/CreateEventPartnerDTO';
import ReserveSpotPartnerDTO from '../../domain/dto/ReserveSportPartnerDTO';

export default abstract class PartnerGateway {
  protected partner_url: string;
  constructor(url: string) {
    this.partner_url = url;
  }
  abstract createEvent(event_partner: CreateEventPartner): Promise<any>;
  abstract getEventById(eventId: string): Promise<any>;
  abstract createSpots(eventId: string, spotName: string): Promise<any>;
  abstract reserveSpot(eventId: string, spots: ReserveSpotPartnerDTO): Promise<any>;
}
