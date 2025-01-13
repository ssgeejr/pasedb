# pip install requests pillow

import requests
from PIL import Image
from io import BytesIO


def download_and_resize_image(image_url, output_path):
    try:
        # Step 1: Download the image
        response = requests.get(image_url)
        response.raise_for_status()  # Raise an exception for HTTP errors

        # Step 2: Open the image from the downloaded content
        image = Image.open(BytesIO(response.content))

        # Step 3: Calculate the new dimensions
        width, height = image.size
        if width > height:
            new_width = 200
            new_height = int((200 / width) * height)
        else:
            new_height = 200
            new_width = int((200 / height) * width)

        # Step 4: Resize the image while maintaining the aspect ratio
        resized_image = image.resize((new_width, new_height), Image.Resampling.LANCZOS)

        # Step 5: Save the resized image
        resized_image.save(output_path)
        print(f"Image successfully downloaded and resized to {new_width}x{new_height}. Saved at {output_path}.")

    except Exception as e:
        print(f"An error occurred: {e}")

# Example usage
image_url = "https://cdn.britannica.com/34/233234-050-1649BFA9/Pug-dog.jpg"  # Replace with your image URL
output_path = "pugs.jpg"  # Path to save the resized image

download_and_resize_image(image_url, output_path)
