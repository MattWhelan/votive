# Votive

Votive is nomination and voting software.

## Design

 * Election
    * Collection of issues
    * State: Nominating, Reconciling, Voting, Closed.
    * Style: Rating, Ranking, etc.
 * Nomination/Alternative
    * An alternative for an issue
    * Has Title, description
 * Issue
    * An issue to be voted on
    * Has alternatives
 * Vote
    * A response to an alternative for an issue.
 * Ballot
    * A collection of votes
 * Tally
    * A reduction across all the ballots in the election, describing the outcome.


## Stories

As an election owner, I can create an election.

As an election owner, I can create an issue for an election.

As an election owner, I can open nominations for an issue.

As an election owner, I can close nominations for an election, to begin reconciling.

As an election owner, I can reconcile nominations for an issue.

As an election owner, I can open voting for a election.

As an election owner, I can close voting for a election, tallying results for each issue.

As a voter, I can submit nominations on an issue open for nominations.

As a voter, I can submit a single ballot for an open election.

As a voter, I can view the results of a closed election.
