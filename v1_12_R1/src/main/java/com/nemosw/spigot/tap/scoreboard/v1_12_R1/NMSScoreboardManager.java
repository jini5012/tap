package com.nemosw.spigot.tap.scoreboard.v1_12_R1;

import com.nemosw.spigot.tap.scoreboard.ScoreboardManager;

public final class NMSScoreboardManager extends ScoreboardManager
{
	@Override
	public NMSScoreboard newScoreboard()
	{
		return new NMSScoreboard();
	}

}
