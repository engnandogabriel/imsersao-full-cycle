import { Title } from "../components/Title";
import { EventModel } from "../models";
import { EventCard } from "../components/EventCard";
import axios from "axios";

export async function getEvents(): Promise<EventModel[]> {
  const { data } = await axios.get(`${process.env.NODE_API_URL}/events`, {
    headers: {},
  });
  const eventsData = data.data;
  return eventsData.events;
}

export default async function HomePage() {
  const events = await getEvents();
  return (
    <main className="mt-10 flex flex-col">
      <Title>Eventos disponíveis</Title>
      <div className="mt-8 sm:grid sm:grid-cols-auto-fit-cards flex flex-wrap justify-center gap-x-2 gap-y-4">
        {events.map((event: EventModel) => (
          <EventCard key={event.id} event={event} />
        ))}
      </div>
    </main>
  );
}
