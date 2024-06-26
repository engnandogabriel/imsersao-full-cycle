"use server";

import axios from "axios";
import { revalidateTag } from "next/cache";
import { cookies } from "next/headers";
import { redirect } from "next/navigation";

export async function selectSpotAction(eventId: string, spotName: string) {
  const cookieStore = cookies();

  const spots = JSON.parse(cookieStore.get("spots")?.value || "[]");
  spots.push(spotName);
  const uniqueSpots = spots.filter(
    (spot: string, index: number) => spots.indexOf(spot) === index
  );
  cookieStore.set("spots", JSON.stringify(uniqueSpots));
  cookieStore.set("eventId", eventId);
}

export async function unselectSpotAction(spotName: string) {
  const cookieStore = cookies();

  const spots = JSON.parse(cookieStore.get("spots")?.value || "[]");
  const newSpots = spots.filter((spot: string) => spot !== spotName);
  cookieStore.set("spots", JSON.stringify(newSpots));
}

// src/actions.ts
export async function clearSpotsAction() {
  const cookieStore = cookies();
  cookieStore.set("spots", "[]");
  cookieStore.set("eventId", "");
}

export async function selectTicketTypeAction(ticketKind: "full" | "half") {
  const cookieStore = cookies();
  cookieStore.set("ticketKind", ticketKind);
}

export async function checkoutAction(
  prevState: any,
  {
    cardHash,
    email,
  }: {
    cardHash: string;
    email: string;
  }
) {
  const cookieStore = cookies();
  const eventId = cookieStore.get("eventId")?.value;
  const spots = JSON.parse(cookieStore.get("spots")?.value || "[]");
  const ticketKind = cookieStore.get("ticketKind")?.value || "full";

  try {
    const { data } = await axios.post(`${process.env.NODE_API_URL}/checkout`, {
      eventId: eventId,
      card_hash: cardHash,
      ticketKind: ticketKind,
      names: spots,
      email,
    });
  } catch (e: any) {
    return { error: "Erro ao realizar a compra" };
  }

  revalidateTag(`events/${eventId}`);
  redirect(`/checkout/${eventId}/success`);
}
