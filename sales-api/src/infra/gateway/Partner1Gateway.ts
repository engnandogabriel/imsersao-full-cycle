import PartnerGateway from '../../application/gateway/PartnerGateway';
import CreateEventPartner from '../../domain/dto/CreateEventPartnerDTO';
import ReserveSpotPartnerDTO from '../../domain/dto/ReserveSportPartnerDTO';
import HttpClient from '../http/HttpClient';

export default class Partner1Gateway extends PartnerGateway {
  constructor(readonly httpClient: HttpClient) {
    super('http://partner-app1:3000');
  }
  async createEvent(event_partner: CreateEventPartner): Promise<any> {
    const response = await this.httpClient.post(`${this.partner_url}/events`, event_partner);
    return response;
  }
  async getEventById(eventId: string): Promise<any> {
    const response = await this.httpClient.get(`${this.partner_url}/events/${eventId}`);
    return response;
  }

  async createSpots(eventId: string, spotName: string): Promise<any> {
    const response = await this.httpClient.post(`${this.partner_url}/events/${eventId}/spots`, { name: spotName });
    return response;
  }
  async reserveSpot(eventId: string, spots: ReserveSpotPartnerDTO): Promise<any> {
    const response = await this.httpClient.post(`${this.partner_url}/events/${eventId}/reserve`, spots);
    return response;
  }
}
