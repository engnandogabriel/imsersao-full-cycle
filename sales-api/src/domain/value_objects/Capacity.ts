import InvalidParamError from '../Exceptions/InvalidParamError';
import NullParamError from '../Exceptions/NullParamError';

export default class Capacity {
  private capacity: number;
  constructor(capacity: number) {
    this.capacity = this.validatecapacity(capacity);
  }
  private validatecapacity(capacity: number): number {
    if (capacity == null || capacity == undefined) throw new NullParamError('Capacity cannot be null');
    if (capacity <= 0) throw new InvalidParamError('Capacity should be greater than zero');
    return capacity;
  }
  getCapacity(): number {
    return this.capacity;
  }
}
