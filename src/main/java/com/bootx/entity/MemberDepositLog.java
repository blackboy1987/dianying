/*
 * Copyright 2005-2017 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.bootx.entity;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Entity - 会员预存款记录
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Entity
public class MemberDepositLog extends BaseEntity<Long> {

	private static final long serialVersionUID = -8323452873046981882L;

	/**
	 * 类型
	 */
	public enum Type {

		/**
		 * 预存款充值
		 */
		recharge,

		/**
		 * 预存款调整
		 */
		adjustment,

		/**
		 * 订单支付
		 */
		orderPayment,

		/**
		 * 订单退款
		 */
		orderRefunds,

		reward
	}

	/**
	 * 类型
	 */
	@JsonView(BaseView.class)
	@Column(nullable = false, updatable = false)
	private Type type;

	/**
	 * 收入金额
	 */
	@JsonView(BaseView.class)
	@Column(nullable = false, updatable = false, precision = 21, scale = 6)
	private BigDecimal credit;

	/**
	 * 支出金额
	 */
	@JsonView(BaseView.class)
	@Column(nullable = false, updatable = false, precision = 21, scale = 6)
	private BigDecimal debit;

	/**
	 * 当前余额
	 */
	@JsonView(BaseView.class)
	@Column(nullable = false, updatable = false, precision = 21, scale = 6)
	private BigDecimal balance;

	/**
	 * 备注
	 */
	@JsonView(BaseView.class)
	@Column(updatable = false)
	private String memo;

	/**
	 * 会员
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, updatable = false)
	private Member member;

	/**
	 * 获取类型
	 * 
	 * @return 类型
	 */
	public Type getType() {
		return type;
	}

	/**
	 * 设置类型
	 * 
	 * @param type
	 *            类型
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * 获取收入金额
	 * 
	 * @return 收入金额
	 */
	public BigDecimal getCredit() {
		return credit;
	}

	/**
	 * 设置收入金额
	 * 
	 * @param credit
	 *            收入金额
	 */
	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	/**
	 * 获取支出金额
	 * 
	 * @return 支出金额
	 */
	public BigDecimal getDebit() {
		return debit;
	}

	/**
	 * 设置支出金额
	 * 
	 * @param debit
	 *            支出金额
	 */
	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	/**
	 * 获取当前余额
	 * 
	 * @return 当前余额
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * 设置当前余额
	 * 
	 * @param balance
	 *            当前余额
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * 获取备注
	 * 
	 * @return 备注
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置备注
	 * 
	 * @param memo
	 *            备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 获取会员
	 * 
	 * @return 会员
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * 设置会员
	 * 
	 * @param member
	 *            会员
	 */
	public void setMember(Member member) {
		this.member = member;
	}

}