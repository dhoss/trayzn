create table bookmarks(
  id integer not null generated always as identity primary key,
  url varchar not null unique,
  title varchar not null unique,
  favicon_url varchar,
  created timestamptz not null default now(),
  updated timestamptz,
  last_snapshot timestamptz
);