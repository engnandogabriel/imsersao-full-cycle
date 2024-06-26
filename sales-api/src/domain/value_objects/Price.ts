import InvalidParamError from '../Exceptions/InvalidParamError';
import NullParamError from '../Exceptions/NullParamError';

export default class Price {
  private price: number;
  constructor(price: number) {
    this.price = this.validatePrice(price);
  }
  private validatePrice(price: number): number {
    if (price == null || price == undefined) throw new NullParamError('Price cannot be null');
    if (price < 0) throw new InvalidParamError('Price cannot be less than zero');
    return price;
  }
  getPrice(): number {
    return this.price;
  }
}
