package com.study.legacy.domain;


public class AppearanceVO {
	// mete infomation
	public enum DescriminaterType {
		Board("tbl_board", int.class), Party("tbl_party", String.class);

		private String targetTbl;
		private Class idType;

		private DescriminaterType(String targetTbl, Class idType) {
			this.targetTbl = targetTbl;
			this.idType = idType;
		}

		public String getTargetTbl() {
			return targetTbl;
		}

		private static DescriminaterType findDescriminaterType(String descriminater) {
			DescriminaterType ret = null;

			for (DescriminaterType ele : DescriminaterType.values()) {
				if (ele.targetTbl.equals(descriminater)) {
					ret = ele;
					break;
				}
			}

			return ret;
		}
	}// enum end --------------------------------------------------------------------------------------------------------------

	private DescriminaterType targetType;
	private String targetId;
	private int lexiconId;
	private String lexicon;
	private int cnt;

	public AppearanceVO(String descriminater, String targetId, int lexiconId, int cnt) {
		this.targetType = DescriminaterType.findDescriminaterType(descriminater);
		this.targetId = targetId;
		this.lexiconId = lexiconId;
		this.cnt = cnt;
	}
	public AppearanceVO(String descriminater, String targetId, String lexicon, int cnt) {
		this.targetType = DescriminaterType.findDescriminaterType(descriminater);
		this.targetId = targetId;
		this.lexicon = lexicon;
		this.cnt = cnt;
	}
	
	public String getTargetType() {
		return targetType.targetTbl;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public int getLexiconId() {
		return lexiconId;
	}
	public void setLexiconId(int lexiconId) {
		this.lexiconId = lexiconId;
	}
	public String getLexicon() {
		return lexicon;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

}
