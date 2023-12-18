export type ParsedSong = {
  id: number;
  title: string;
  duration: number;
  mp3Path: string;
  year: string;
  band: {
    id: number;
    name: string;
  };
  manager: {
    id: number;
    name: string;
  };
  supplier: {
    id: number;
    name: string;
  };
};
