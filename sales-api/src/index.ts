import CreateEvent from './application/usecases/CreateEvent';
import EventsController from './controller/EventsController';
import MysqlAdpter from './infra/database/MysqlAdpter';
import CreatePartner from './infra/gateway/FactoryPartner';
import EventRepositoryDataBase from './infra/repository/EventRepositoryDataBase';
import ExpressAdpter from './infra/http/ExpressAdpter';
import CreateSpots from './application/usecases/CreateSpots';
import SpotRepositoryDataBase from './infra/repository/SpotRepositoryDataBase';
import BuyTicket from './application/usecases/BuyTicket';
import TicketRepositoryDataBase from './infra/repository/TicketRepositoryDataBase';
import GetEvent from './application/usecases/GetEvent';
import ListEvents from './application/usecases/ListEvents';
import EventsDataBasaDAO from './infra/dao/EventsDataBaseDAO';
import SpotsDataBaseDAO from './infra/dao/SpotsDataBaseDAO';
import ListSpots from './application/usecases/ListSpots';

const createSpots = new CreateSpots(new EventRepositoryDataBase(new MysqlAdpter()), new SpotRepositoryDataBase(new MysqlAdpter()), new CreatePartner());
const createEvent = new CreateEvent(new EventRepositoryDataBase(new MysqlAdpter()), new CreatePartner(), createSpots);
const buyTicket = new BuyTicket(new EventRepositoryDataBase(new MysqlAdpter()), new SpotRepositoryDataBase(new MysqlAdpter()), new TicketRepositoryDataBase(new MysqlAdpter()), new CreatePartner());
const getEvent = new GetEvent(new EventRepositoryDataBase(new MysqlAdpter()));
const listEvents = new ListEvents(new EventsDataBasaDAO(new MysqlAdpter()));
const listSpots = new ListSpots(new SpotsDataBaseDAO(new MysqlAdpter()));
const httpServer = new ExpressAdpter();
new EventsController(httpServer, createEvent, createSpots, buyTicket, getEvent, listEvents, listSpots);
httpServer.listen(8080);
