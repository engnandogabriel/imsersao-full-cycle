export default class BadRequestError {
  readonly message: string;
  readonly name: string;
  constructor(message: string) {
    this.message = message;
    this.name = 'BadRequestError';
  }
}
