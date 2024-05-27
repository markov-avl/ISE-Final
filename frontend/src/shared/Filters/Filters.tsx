import React, { ReactNode } from 'react'
import Filter from '@/shared/Filter/Filter'
import { DataCustomNameByField } from '@/types/DataCustomNameByField'
import { FilterObject } from '@/components/VideoGamesTable/VideoGames'

interface FiltersProps<Type, Key extends keyof Type> {
	selectedFilters: FilterObject<Type>
	filters: DataCustomNameByField<Type, Key>[]
	setSelectedFilters: (filter: FilterObject<Type>) => void
}

const Filters = <Type, Key extends keyof Type>({
	selectedFilters,
	filters,
	setSelectedFilters
}: FiltersProps<Type, Key>): ReactNode => {
	return (
		<>
			{filters.map(({ name, key, data }) => (
				<Filter
					key={key as string}
					title={name}
					data={data}
					onChangeHandler={e => {
						const selectedValues: string[] = []
						for (const option of e.currentTarget.selectedOptions) {
							selectedValues.push(option.value)
						}
						const newState = {
							...selectedFilters,
							[key]: selectedValues
						}
						setSelectedFilters(newState)
					}}
				/>
			))}
		</>
	)
}

export default Filters
