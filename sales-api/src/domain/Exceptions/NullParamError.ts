import BadRequestError from './BadRequestError';

export default class NullParamError extends BadRequestError {
  name: string;
  message: string;
  constructor(message: string) {
    super(message);
    this.name = 'NullParamError';
    this.message = message;
  }
}
