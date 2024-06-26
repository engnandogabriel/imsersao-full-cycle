export default interface HttpServer {
  on(method: string, url: string, callback: Function): Promise<any>;
  listen(port: number): void;
}
