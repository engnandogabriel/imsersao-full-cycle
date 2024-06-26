import UnprocessableError from './UnprocessableErro';

export default class UnauthorizedError extends UnprocessableError {
  name: string;
  message: string;
  constructor(message: string) {
    super(message);
    this.name = 'UnauthorizedError';
    this.message = message;
  }
}
