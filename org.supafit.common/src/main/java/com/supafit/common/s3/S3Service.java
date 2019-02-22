package com.supafit.common.s3;

import com.supafit.cloud.exception.S3AccessException;
import com.supafit.cloud.s3.access.AmazonS3Service;
import com.supafit.cloud.s3.access.PresignedS3URLService;
import com.supafit.cloud.s3.model.GenerationParams;
import com.supafit.cloud.s3.model.S3Options;
import com.supafit.dao.user.model.User;

public class S3Service {

	private final String ACCESS_KEY = "AKIAJD3OQVNTI56LBDZA";
	private final String ACCESS_SECRET_KEY = "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";

	private S3Options s3options = null;
	private PresignedS3URLService s3UrlService = null;
	private final String POD_URL = "https://s3-ap-southeast-1.amazonaws.com";
	private final String POD = "s3-ap-southeast-1.amazonaws.com";

	public S3Service(String bucketName) {
		s3options = new S3Options(bucketName, ACCESS_KEY, ACCESS_SECRET_KEY);
		s3UrlService = new PresignedS3URLService(s3options);
	}

	public String getUserDPDownloadUrl(User user) {

//		int images = user.getImages();
		long userId = user.getId();
//		String imageName = images + ".jpg";
		String imageURL = s3UrlService.getPresignedDownloadS3URL(
				"user/images/dp/"+userId+"/" + null, null);
		return imageURL;
	}

	/*public List<String> getCaseImageDownloadUrls(Case medCase,
			int numberOfImages) {

		List<String> imageURLs = new ArrayList<String>();
		if (numberOfImages > 0) {
			for (int i = 1; i < numberOfImages + 1; i++) {

				String imageURL = s3UrlService.getPresignedDownloadS3URL(
						"case/images/" + medCase.getId() + "/" + i + ".jpg", i
								+ ".jpg");
				imageURLs.add(imageURL);

			}
		}
		return imageURLs;
	}*/

	/*public String getContributionImageDownloadUrl(Contribution contribution) {

		long caseId = contribution.getCaseId();
		long contributionId = contribution.getId();
		String imageName = contributionId + ".jpg";
		String imageURL = s3UrlService.getPresignedDownloadS3URL(
				"contribution/" + caseId + "/images/" + imageName, imageName);
		return imageURL;
	}*/

	public String getUserDPUploadUrl(User user) {
		
		Integer imageName = null;
		long userId = user.getId();
		GenerationParams params = new GenerationParams("binary/octet-stream",
				1000000l, "user/images/dp/"+userId+"/" + imageName + ".jpg", true);
		String imageURL = s3UrlService.getPresignedUploadS3URL(params);
		return imageURL;
	}
	
	public String getUserDPUploadUrl(long userId) {
		GenerationParams params = new GenerationParams("binary/octet-stream",
				1000000l, "user/images/dp/" + userId + ".jpg", true);
		String imageURL = s3UrlService.getPresignedUploadS3URL(params);
		return imageURL;
	}

	/*public List<String> getCaseImageUploadUrls(Case elyxCase, int numberOfImages) {

		GenerationParams params = null;
		List<String> imageURLs = new ArrayList<String>();

		for (int i = 1; i <= numberOfImages; i++) {
			params = new GenerationParams("case/images/" + elyxCase.getId()
					+ "/" + i + ".jpg", true);
			String imageURL = s3UrlService.getPresignedUploadS3URL(params);
			imageURLs.add(imageURL);
		}
		return imageURLs;
	}

	public String getContributionImageUploadUrl(Contribution contribution) {
		GenerationParams params = new GenerationParams("contribution/"
				+ contribution.getCaseId() + "/images/" + contribution.getId()
				+ ".jpg", true);
		String imageURL = s3UrlService.getPresignedUploadS3URL(params);
		return imageURL;
	}*/

	public int createBucket(String bucketName) throws S3AccessException {
		AmazonS3Service amazonS3Service = new AmazonS3Service(POD, s3options);
		return amazonS3Service.createBucket(bucketName, POD_URL);
	}

	public String getUserDPDownloadUrl(long userId) {
		String imageName = userId + ".jpg";
		String imageURL = s3UrlService.getPresignedDownloadS3URL(
				"user/images/dp/" + imageName, imageName);
		return imageURL;
	}
}
