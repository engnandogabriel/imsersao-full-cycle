import { Ratting } from '../Enums';
import InvalidParamError from '../Exceptions/InvalidParamError';
import NullParamError from '../Exceptions/NullParamError';
import Capacity from '../value_objects/Capacity';
import Name from '../value_objects/Name';
import Price from '../value_objects/Price';
import ValidateDate from '../value_objects/ValidateDate';
export default class Events {
  private id: string;
  private name: string;
  private location: string;
  private organization: string;
  private rating: Ratting;
  private date: Date;
  private image_url: string;
  private capacity: number;
  private price: number;
  private partner_id: number;
  private event_partner: string;

  constructor(id: string, name: string, location: string, organization: string, rating: Ratting, date: Date, image_url: string, capacity: number, price: number, partner_id: number, event_partner: string) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.organization = organization;
    this.rating = rating;
    this.date = date;
    this.image_url = image_url;
    this.capacity = capacity;
    this.price = price;
    this.partner_id = partner_id;
    this.event_partner = event_partner;
  }
  public static create(name: string, location: string, organization: string, rating: string, date: string, image_url: string, capacity: number, price: number, partner_id: number): Events {
    const validRatting = Events.convertToRatting(rating);
    const id = crypto.randomUUID();
    if (rating == null || rating == undefined) throw new NullParamError('Rating cannot be null');
    if (partner_id == null || partner_id == undefined) throw new NullParamError('Partner id cannot be null');
    return new Events(id, new Name(name).getName(), location, organization, validRatting, new ValidateDate(date).getDate(), image_url, new Capacity(capacity).getCapacity(), new Price(price).getPrice(), partner_id, '');
  }
  public static restore(id: string, name: string, location: string, organization: string, rating: Ratting, date: Date, image_url: string, capacity: number, price: number, partner_id: number, event_partner: string): Events {
    return new Events(id, name, location, organization, rating, new Date(date), image_url, capacity, price, partner_id, event_partner);
  }

  private static convertToRatting(rating: string): Ratting {
    if (Object.values(Ratting).includes(rating as Ratting)) {
      return rating as Ratting;
    } else {
      throw new InvalidParamError(`Invalid rating value: ${rating}`);
    }
  }

  // Getters
  public getId(): string {
    return this.id;
  }

  public getName(): string {
    return this.name;
  }

  public getLocation(): string {
    return this.location;
  }

  public getOrganization(): string {
    return this.organization;
  }

  public getRatting(): Ratting {
    return this.rating;
  }

  public getDate(): Date {
    return this.date;
  }

  public getImageUrl(): string {
    return this.image_url;
  }

  public getCapacity(): number {
    return this.capacity;
  }

  public getPrice(): number {
    return this.price;
  }

  public getPartnerId(): number {
    return this.partner_id;
  }
  public setEventPartner(event_partner: string) {
    this.event_partner = event_partner;
  }
  public getEventPartner(): string {
    return this.event_partner;
  }
}
