package com.nemosw.spigot.tap.scoreboard.v1_12_R1;

import com.nemosw.spigot.tap.scoreboard.Score;

public final class NMSScore implements Score
{
	final NMSObjective objective;
	
	final String name;
	
	int score;
	
	private boolean valid;
	
	NMSScore(NMSObjective objective, String name, int score)
	{
		this.objective = objective;
		this.name = name;
		this.score = score;
		this.valid = true;
	}
	
	@Override
	public NMSObjective getObjective()
	{
		return this.objective;
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public int getScore()
	{
		return this.score;
	}

	@Override
	public void setScore(int score)
	{
		checkState();
		
		if (this.score == score)
			return;
		
		this.score = score;
		
		this.objective.scoreboard.sendAll(FakeScore.getInstance(this).createUpdatePacket());
	}
	
	@Override
	public void unregister()
	{
		checkState();
		
		this.objective.unregisterScore(this);
		remove();
	}
	
	void remove()
	{
		this.valid = false;
	}
	
	private void checkState()
	{
		if (!this.valid)
			throw new IllegalStateException("Invalid Score '" + this.name + "' @" + Integer.toHexString(System.identityHashCode(this)));
	}
}
