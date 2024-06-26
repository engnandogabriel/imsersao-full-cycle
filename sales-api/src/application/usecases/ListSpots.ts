import { isConciseBody } from 'typescript';
import BadRequestError from '../../domain/Exceptions/BadRequestError';
import UnprocessableError from '../../domain/Exceptions/UnprocessableErro';
import { badRequest, serverError, success, unprocessableEntity } from '../../domain/Response/Helpers';
import SpotsDAO from '../dao/SpotsDAO';

export default class ListSpots {
  private spotsDAO: SpotsDAO;
  constructor(spotsDAO: SpotsDAO) {
    this.spotsDAO = spotsDAO;
  }
  async execute(spotId: string) {
    try {
      const spots = await this.spotsDAO.listAll(spotId);
      return success({ message: 'Success', data: spots });
    } catch (error: any) {
      if (error instanceof UnprocessableError) return unprocessableEntity(error);
      if (error instanceof BadRequestError) return badRequest(error);
      return serverError(new Error('Internal Sever Error'));
    }
  }
}
