create table bookmarks(
  id integer not null generated always as identity primary key,
  uuid uuid not null unique,
  url varchar not null unique,
  title varchar not null unique,
  favicon_url varchar,
  created timestamptz default now(),
  updated timestamptz,
  last_snapshot timestamptz
);