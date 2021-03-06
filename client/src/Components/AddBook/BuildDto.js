export const buildBookRequestDto = values => {
    return {
        "name": values?.bookName,
        "author": values?.authorName,
        "copies": values?.numberOfCopies,
        "existingCopies": values?.numberOfCopies,
        "location": values?.bookLocation,
        "subject": values?.bookSubject
    };
}