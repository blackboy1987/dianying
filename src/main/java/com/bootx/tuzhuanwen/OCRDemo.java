

package com.bootx.tuzhuanwen;

import com.alibaba.fastjson.JSONObject;
import com.bootx.util.JsonUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;

import java.io.File;

public final class OCRDemo {

	public static ResultResponse AKSKDemo(File file){
		String AK="K7G5RJ6WHQWWWA4SZ00A"; //AK from authentication
		String SK="YHp8FJCZ8M6WNjOj97PB70AujNVz4mffSMpHcJju"; //SK from authentication
		String regionName="cn-south-1"; //region name of the service
		String httpUri = "/v1.0/ocr/web-image";
		JSONObject params = new JSONObject();
		try {
			HWOcrClientAKSK ocrClient=new HWOcrClientAKSK(regionName, AK, SK);
			HttpResponse response=ocrClient.RequestOcrServiceBase64(httpUri, file.getAbsolutePath(), params);
			System.out.println(response);
			String content = IOUtils.toString(response.getEntity().getContent(), "utf-8");
			ResultResponse resultResponse = JsonUtils.toObject(content, ResultResponse.class);
			return resultResponse;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
