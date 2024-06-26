import { TicketType } from '../Enums';
import InvalidParamError from '../Exceptions/InvalidParamError';

export default class Ticket {
  private id: string;
  private eventId: string;
  private spotId: string;
  private ticket_kind: TicketType;
  private price: number;

  constructor(id: string, eventId: string, spotId: string, ticketType: TicketType, price: number) {
    this.id = id;
    this.eventId = eventId;
    this.spotId = spotId;
    this.ticket_kind = ticketType;
    this.price = price;
  }
  public static create(eventId: string, spotId: string, ticketType: string, price: number) {
    const id = crypto.randomUUID();
    const type = this.convertTicketType(ticketType);
    if (type == 'half') price = price / 2;
    return new Ticket(id, eventId, spotId, type, price);
  }
  public static restore(id: string, eventId: string, spotId: string, ticketType: TicketType, price: number) {
    return new Ticket(id, eventId, spotId, ticketType, price);
  }
  private static convertTicketType(ticketType: string): TicketType {
    if (Object.values(TicketType).includes(ticketType as TicketType)) return ticketType as TicketType;
    throw new InvalidParamError(`Invalid ticket type value: ${ticketType}`);
  }
  public getId(): string {
    return this.id;
  }

  public getEventId(): string {
    return this.eventId;
  }

  public getSpotId(): string {
    return this.spotId;
  }

  public getTicketKind(): string {
    return this.ticket_kind;
  }

  public getPrice(): number {
    return this.price;
  }
}
