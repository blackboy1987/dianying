
package com.bootx.listener;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * Listener - 缓存
 * 
 * @author bootx Team
 * @version 6.1
 */
@Component
public class CacheEventListener extends CacheEventListenerAdapter {

	/**
	 * 元素过期调用
	 * 
	 * @param ehcache
	 *            缓存
	 * @param element
	 *            元素
	 */
	@Override
	public void notifyElementExpired(Ehcache ehcache, Element element) {
		String cacheName = ehcache.getName();
	}

}