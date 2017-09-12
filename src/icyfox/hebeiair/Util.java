package icyfox.hebeiair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Util {

	public static String HttpGet(String url) throws ClientProtocolException, IOException {
		//�½�һ��Ĭ�ϵ�����
		DefaultHttpClient client = new DefaultHttpClient();
		//�½�һ��Get����
		HttpGet get = new HttpGet(url);
		//�õ�����Ļ�Ӧ
		HttpResponse response = client.execute(get);
		//��õ���ҳԴ���루xml��
		String content = null;

		//�����������Ӧ����OK�Ļ���
		if (response.getStatusLine().getStatusCode() == 200) {
			//�����ǰ��������ݷֶζ�ȡ�����Ĺ���
			InputStream in = response.getEntity().getContent();
			byte[] data = new byte[1024];
			int length = 0;
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			while ((length = in.read(data)) != -1) {
				bout.write(data, 0, length);
			}
			//�����ֽ���תΪ�ַ��� ת���ı���Ϊutf-8.
			content = new String(bout.toByteArray(), "utf-8");
		}
		//���صõ����ַ��� Ҳ������ҳԴ����
		return content;
	}
}
