'use client'

import React from "react";

export interface ICollapseProps{
  children?: React.ReactNode;
  title: string;
}

export function Collapse({children, title}:ICollapseProps){
  const [showContent, setShowContent] = React.useState(false);

  const toggleShowContent = () => {
    setShowContent(!showContent);
  }

  return (
      <div className="bg-gray-700 rounded-lg relative m-10">
        <div className="flex items-start justify-between p-5 border-b rounded-t" onClick={toggleShowContent}>
          <h3 className="text-xl font-semibold">
            {title}
          </h3>
        </div>
        {showContent &&
          <div className="p-6 space-y-6">
            {children}
          </div>
        }
      </div>
  );
}