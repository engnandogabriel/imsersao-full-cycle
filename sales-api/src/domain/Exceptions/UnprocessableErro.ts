export default class UnprocessableError {
  readonly message: string;
  readonly name: string;
  constructor(message: string) {
    this.message = message;
    this.name = 'UnprocessableError';
  }
}
