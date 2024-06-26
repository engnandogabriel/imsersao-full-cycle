import InvalidParamError from '../Exceptions/InvalidParamError';
import NullParamError from '../Exceptions/NullParamError';

export default class ValidateDate {
  private date: Date;

  constructor(date: string) {
    this.date = this.validateDate(date);
  }

  private validateDate(date: string): Date {
    if (date == null || date == undefined) throw new NullParamError('Date cannot be null');
    const isoDateRegex = /^\d{4}-\d{2}-\d{2}$/;
    if (!isoDateRegex.test(date)) {
      throw new InvalidParamError('Invalid date format. Date must be in ISO format.');
    }

    const parsedDate = new Date(date);
    const currentDate = new Date();
    currentDate.setHours(0, 0, 0, 0);

    // if (parsedDate.getTime() < currentDate.getTime()) {
    //   throw new Error('Event date must be in the future.');
    // }

    return parsedDate;
  }

  getDate() {
    return this.date;
  }
}
