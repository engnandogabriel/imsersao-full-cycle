import InvalidParamError from '../Exceptions/InvalidParamError';
import NullParamError from '../Exceptions/NullParamError';

export default class Name {
  private name: string;
  constructor(name: string) {
    this.name = this.validateName(name);
  }

  validateName(name: string): string {
    if (name == null || name == undefined) throw new NullParamError('Event name is required');
    if (name.length < 5) throw new InvalidParamError('Name length must be greater than 5');
    return name;
  }

  getName() {
    return this.name;
  }
}
