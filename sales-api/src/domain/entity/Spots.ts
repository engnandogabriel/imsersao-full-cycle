import { Status } from '../Enums';
import InvalidParamError from '../Exceptions/InvalidParamError';

export default class Spot {
  private id: string;
  private eventId: string;
  private name: string;
  private status: Status;
  private ticketId: string;

  constructor(id: string, eventId: string, name: string, status: Status, ticketId: string) {
    this.id = id;
    this.eventId = eventId;
    this.name = name;
    this.status = status;
    this.ticketId = ticketId;
  }
  public static create(eventId: string, name: string): Spot {
    const id = crypto.randomUUID();
    return new Spot(id, eventId, name, Status.Available, '');
  }

  public static restore(id: string, eventId: string, name: string, status: string, ticketId: string) {
    return new Spot(id, eventId, name, this.convertToStatus(status), ticketId);
  }

  private static convertToStatus(status: string): Status {
    if (Object.values(Status).includes(status as Status)) return status as Status;
    throw new InvalidParamError(`Invalid status value: ${status}`);
  }
  public static generateSpots(eventId: string, numberOfSpots: number) {
    const spots: Spot[] = [];
    const alphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    const spotsPerLetter = 10;

    for (let i = 0; i < numberOfSpots; i++) {
      const letterIndex = Math.floor(i / spotsPerLetter);
      const letter = alphabet[letterIndex];
      const number = (i % spotsPerLetter) + 1;
      const spotsName = `${letter}${number}`;
      spots.push(Spot.create(eventId, spotsName));
    }

    return spots;
  }

  public getId(): string {
    return this.id;
  }

  public getEventId(): string {
    return this.eventId;
  }

  public getName(): string {
    return this.name;
  }
  public setSold() {
    this.status = Status.Sold;
  }
  public getStatus(): Status {
    return this.status;
  }

  public getTicketId(): string {
    return this.ticketId;
  }
  public setTicketId(ticketId: string) {
    if (ticketId != null || ticketId != undefined) this.ticketId = ticketId;
  }
}
