import axios from 'axios';
import HttpClient from './HttpClient';

export default class AxiosAdapter implements HttpClient {
  async get(url: string): Promise<any> {
    try {
      const response = await axios.get(url);
      return response.data;
    } catch (e: any) {
      return e.response.data;
    }
  }
  async post(url: string, data: any): Promise<any> {
    try {
      const response = await axios.post(url, data);
      return response.data;
    } catch (e: any) {
      return { status: e.response.status, data: e.response.data };
    }
  }
}
