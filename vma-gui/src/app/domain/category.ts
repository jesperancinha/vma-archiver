import {Artist} from "./artist";
import {Song} from "./song";

export interface Category {
  id: string;
  category: string,
  type: string,
  capacity: number,
  artists: Artist[],
  songs: Song[],
  selectedArtist?: string,
  selectedSong?: string
}
