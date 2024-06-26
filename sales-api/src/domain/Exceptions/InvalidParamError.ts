import UnprocessableError from './UnprocessableErro';

export default class InvalidParamError extends UnprocessableError {
  name: string;
  message: string;
  constructor(message: string) {
    super(message);
    this.message = message;
    this.name = 'InvalidParamErro';
  }
}
