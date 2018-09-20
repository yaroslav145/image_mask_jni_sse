#include "image.h"
#include <emmintrin.h>
#include <stdio.h>

JNIEXPORT void JNICALL Java_image_maskImg(JNIEnv *e, jclass obj, jintArray data, jint count)
{
	jint *p = (*e)->GetIntArrayElements(e, data, 0);
	int mask[4];
	int i;
	int select;

	printf("Select:\n 1 - Enhance blue\n 2 - Set mask\n");
	scanf("%i", &select);

	if(select == 1)
	{
		printf("Enter amplification\n");
		scanf("%i", &mask[0]);
		mask[1] = mask[0];
		mask[2] = mask[0];
		mask[3] = mask[0];
	}
	else
	{
		printf("Enter mask (format: 0xFFFFFF)\n");
		scanf("%i",&mask[0]);
		mask[1] = mask[0];
		mask[2] = mask[0];
		mask[3] = mask[0];
	}

	for(i = 0; i < count; i+=4)
	{
		__m128i v1, v2;

		v1 = _mm_load_si128((__m128i*) &p[i]);
		v2 = _mm_load_si128((__m128i*) mask);

		v1 = _mm_or_si128(v1, v2);

		_mm_store_si128((__m128i*) &p[i], v1);
	}


	(*e)->ReleaseIntArrayElements(e, data, p, 0);
}
