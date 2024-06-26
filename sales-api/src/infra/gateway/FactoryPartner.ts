import PartnerGateway from '../../application/gateway/PartnerGateway';
import InvalidParamError from '../../domain/Exceptions/InvalidParamError';
import AxiosAdapter from '../http/AxiosAdpters';
import Partner1Gateway from './Partner1Gateway';
import Partner2Gateway from './Partner2Gateway';

interface FactoryPartner {
  create(partner_id: number): PartnerGateway;
}

export default class CreatePartner implements FactoryPartner {
  create(partner_id: number): PartnerGateway {
    if (partner_id == 1) return new Partner1Gateway(new AxiosAdapter());
    if (partner_id == 2) return new Partner2Gateway(new AxiosAdapter());
    throw new InvalidParamError('Partner id invalid');
  }
}
