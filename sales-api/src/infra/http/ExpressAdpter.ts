import HttpResponse from '../../domain/Response/ResponseHttp';
import HttpServer from './HttpServer';
import express, { Request, Response } from 'express';
import cors from 'cors';
export default class ExpressAdpter implements HttpServer {
  app: any;
  constructor() {
    this.app = express();
    this.app.use(express.json());
    this.app.use(cors());
  }
  async on(method: string, url: string, callback: Function): Promise<any> {
    this.app[method](url, async function (req: Request, res: Response) {
      try {
        const output: HttpResponse = await callback(req);
        return res.status(output.statusCode).json(output.body);
      } catch (error: any) {
        res.status(error.status).json(error.body);
      }
    });
  }
  listen(port: number): void {
    this.app.listen(port, () => console.log('Server running in port: ' + port));
  }
}
