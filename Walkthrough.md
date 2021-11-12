# VMA Awards Walk-through

## Voting Phase

During the voting phase, users can pick their artist or song and they only can vot for it once.
Double voting is not allowed.
Voters will register at least one single vote but simultaneously, they will try to add an extra vote.
Because the system is implemented with optmistic locking, this extra vote will be registered or not depending if changes have happened in the database row when the user read the record, updated it and tried to save it to the databsase.
This means that, with many users, the likelyhood of a successful extra vote are low, but can make a difference in determining the winner of a particular category.

## Downtime

After votes are recovered, follows a 5 minute break where the votes are counted. In our demo, locally we don't have a lot of records and are limited to our machine power. This means that 5 minutes is a more than enough waiting time.
